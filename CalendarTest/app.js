class Schedule {

  constructor(name, description, date, time) {

  }

}

const ls = localStorage;


// Calendar main
const Calendar = {

  init() {
    const today = new Date();
    Calendar.showDates(today.getFullYear(), today.getMonth() + 1);

    Calendar.year = today.getFullYear();
    Calendar.month = today.getMonth() + 1;

    Calendar.evtHandle();
  },

  evtHandle() {
    document.querySelectorAll(".date")
      .forEach(elem => {
        elem.onclick = function () {
          const day = this.innerText;

          document.querySelector(".modal.schedule")
          .classList.add("show");

          const $mTitle = document.querySelector(".modal.schedule .modal-title");
          $mTitle.innerHTML = `Schedules<br>in ${Calendar.year}. ${Calendar.month}. / ${day}.`
        }
      })
  },

  showDates(y, m) {
    const before = document.querySelectorAll(".date");
    before.forEach(v => v.remove());

    for (
      let i = -Calendar.getFirstDay(y, m) + 1;
      i <= Calendar.getLastDate(y, m);
      i++
    ) {

      Calendar.$calendar.innerHTML += `
        <div class="date ${i < 1 ? "hidden-date" : ""}">
          <p>${i}</p>
        </div>
      `;
    }
  },

  getFirstDay(y, m) {
    const date = new Date(`${y}-${m}-01`);
    return date.getDay();
  },

  getLastDate(y, m) {
    const date = new Date(y, m, 0);
    return date.getDate();
  }


};