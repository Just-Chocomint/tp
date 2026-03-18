package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests for {@code MarkAsFavouriteCommand}.
 */
public class MarkAsFavouriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Tests that executing the command with an out-of-range index fails.
     */
    @Test
    public void execute_indexOutOfRange() {
        Index index = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);
        MarkAsFavouriteCommand markAsFavouriteCommand = new MarkAsFavouriteCommand(index);
        assertCommandFailure(markAsFavouriteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Tests that executing the command on a person who is already marked as favourite fails.
     */
    @Test
    public void execute_personAlreadyInFavourites_throwsCommandException() throws Exception {
        Index index = Index.fromOneBased(1);

        new MarkAsFavouriteCommand(index).execute(model);

        MarkAsFavouriteCommand secondCommand = new MarkAsFavouriteCommand(index);

        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit = lastShownList.get(index.getZeroBased());

        assertCommandFailure(secondCommand, model,
                String.format(MarkAsFavouriteCommand.MESSAGE_MARK_PERSON_DUPLICATE,
                        personToEdit.getName()));
    }

    /**
     * Tests that executing the command with a valid index successfully marks the person as favourite.
     */
    @Test
    public void execute_validMarkAsFavourites() {
        Model testModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Index index = Index.fromOneBased(1);

        List<Person> lastShownList = testModel.getFilteredPersonList();
        Person personToEdit = lastShownList.get(index.getZeroBased());

        Person markedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                personToEdit.getDetails(),
                personToEdit.getTags(),
                true);

        testModel.setPerson(personToEdit, markedPerson);

        MarkAsFavouriteCommand command = new MarkAsFavouriteCommand(index);
        assertCommandSuccess(command, new ModelManager(getTypicalAddressBook(), new UserPrefs()),
                String.format(MarkAsFavouriteCommand.MESSAGE_MARK_PERSON_SUCCESS, personToEdit.getName()),
                testModel);
    }


}
