import { ref } from 'vue';

const ls = localStorage;

export function useScheduleManager() {
  const schedules = ref([]);

  const loadSchedule = () => {
    if (!ls['schedules']) return;
    schedules.value = JSON.parse(ls['schedules']);
  };

  const saveSchedule = () => {
    ls['schedules'] = JSON.stringify(schedules.value);
  };

  const addSchedule = () => {
    const title = prompt("What is the title of the schedule?");
    if (!title.match(/^[a-zA-Z0-9ㄱ-힣 !]*$/g))
      return alert("Incorrect title.");

    const id = new Date().getTime();
    schedules.value.push({
      id,
      date: `${Calendar.year}-${Calendar.month}-${Calendar.day}`,
      title
    });

    saveSchedule();
    refreshScheduleList();
  };

  const removeSchedule = (id) => {
    const index = schedules.value.findIndex(v => v.id == id);
    schedules.value.splice(index, 1);
    saveSchedule();
    refreshScheduleList();
  };

  const refreshScheduleList = () => {
    // This will trigger updates in the components
  };

  return { schedules, loadSchedule, saveSchedule, addSchedule, removeSchedule, refreshScheduleList };
}
