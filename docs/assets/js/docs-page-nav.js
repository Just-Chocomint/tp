document.addEventListener("DOMContentLoaded", () => {
  const navList = document.getElementById("docs-page-nav-list");
  const content = document.querySelector(".docs-content .post-content");

  if (!navList || !content) {
    return;
  }

  const headings = Array.from(content.querySelectorAll("h2, h3"))
    .filter((heading) => heading.id)
    .filter((heading) => !heading.closest(".footnotes"));

  if (!headings.length) {
    return;
  }

  navList.innerHTML = headings
    .map((heading) => {
      const levelClass = heading.tagName.toLowerCase() === "h3"
        ? "docs-page-nav__item docs-page-nav__item--child"
        : "docs-page-nav__item";
      return `<li class="${levelClass}"><a class="docs-page-nav__link" href="#${heading.id}">${heading.textContent.trim()}</a></li>`;
    })
    .join("");

  const links = Array.from(navList.querySelectorAll(".docs-page-nav__link"));
  const sections = headings.map((heading, index) => ({
    heading,
    link: links[index],
  }));

  const setActiveLink = () => {
    let activeSection = sections[0];
    const offset = 140;

    sections.forEach((section) => {
      if (section.heading.getBoundingClientRect().top - offset <= 0) {
        activeSection = section;
      }
    });

    links.forEach((link) => link.classList.remove("is-active"));
    if (activeSection && activeSection.link) {
      activeSection.link.classList.add("is-active");
    }
  };

  setActiveLink();
  document.addEventListener("scroll", setActiveLink, { passive: true });
});
