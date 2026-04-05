document.addEventListener("DOMContentLoaded", () => {
  const input = document.getElementById("docs-search-input");
  const results = document.getElementById("docs-search-results");
  const sidebar = document.querySelector(".docs-sidebar");

  if (!input || !results || !sidebar) {
    return;
  }

  const links = Array.from(sidebar.querySelectorAll("a"))
    .map((link) => ({
      text: link.textContent.trim(),
      href: link.getAttribute("href"),
    }))
    .filter((entry) => entry.text && entry.href);

  const uniqueLinks = links.filter((entry, index, array) =>
    array.findIndex((candidate) => candidate.href === entry.href && candidate.text === entry.text) === index
  );

  const clearResults = () => {
    results.innerHTML = "";
    results.hidden = true;
  };

  const renderResults = (matches) => {
    if (!matches.length) {
      results.innerHTML = '<li class="docs-search__empty">No matches found</li>';
      results.hidden = false;
      return;
    }

    results.innerHTML = matches
      .slice(0, 8)
      .map((match) => `<li><a class="docs-search__result-link" href="${match.href}">${match.text}</a></li>`)
      .join("");
    results.hidden = false;
  };

  input.addEventListener("input", (event) => {
    const query = event.target.value.trim().toLowerCase();

    if (!query) {
      clearResults();
      return;
    }

    const matches = uniqueLinks.filter((entry) => entry.text.toLowerCase().includes(query));
    renderResults(matches);
  });

  document.addEventListener("click", (event) => {
    if (!sidebar.contains(event.target)) {
      clearResults();
    }
  });

  input.addEventListener("keydown", (event) => {
    if (event.key === "Escape") {
      input.value = "";
      clearResults();
    }
  });
});
