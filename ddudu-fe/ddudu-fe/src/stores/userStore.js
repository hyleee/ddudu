import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

const REST_USER_API = "http://localhost:8080/user"
const REST_API = "http://localhost:8080"

export const useUserStore = defineStore('user', () => {

  const user = ref({
    profile: '',
    id: '',
    password: '',
    name: '',
    email: '',
    age: '',
    height: '',
    weight: '',
    area: '',
  })

  const userCreate = function(user){
    axios({
      url : REST_USER_API,
      method: 'POST',
      data: user
    })
    .then(()=>{
      router.push({name: 'userList'})
    })
    .catch((e)=>{
      console.log(e)
    })
  }


  return { useUserStore, user }
})
