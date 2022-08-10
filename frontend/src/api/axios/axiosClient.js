import axios from "axios";
import {getToken} from "./jwt/jwtLocalStorage";

export const SERVER_PATH = process.env.REACT_APP_SERVER_PATH

export const requestConfig = {
    headers: {
        Authorization: "Bearer " + getToken()
    }
}

/*USERS*/
export const axiosGetAllUsers = () => {
    return axios.get(`${SERVER_PATH}/users`, requestConfig)
}
export const axiosCreateOrUpdateUser = (user) => {
    return axios.post(`${SERVER_PATH}/users`,
        {
            id: 0,
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            birthdate: user.birthdate,
            city: user.city,
            phone: "string",
            password: "string",
            registrationDate: "2022-08-06",
            active: true
        }
        , requestConfig)
}
export const axiosRegisterUser = (user) => {
    return axios.post(`${SERVER_PATH}/users/registration`, {
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        birthdate: user.birthdate,
        city: user.city,
        password: user.password,
        role: user.role
    })
}
export const axiosLogin = (user) => {
    return axios.post(`${SERVER_PATH}/users/login`, {
        email: user.email,
        password: user.password
    })
}
export const axiosGetUserById = (userId) => {
    return axios.get(`${SERVER_PATH}/users/${userId}`, requestConfig)
}

/*COURSES*/

export const axiosCreateOrUpdateCourse = (course) => {
    return axios.post(`${SERVER_PATH}/courses`,
        {
            id: course.courseId,
            name: course.name,
            description: course.description,
            isPrivate: course.isPrivate,
            cover: course.cover,
            status: course.status,
            interestTags: [
                ...(course.interestTags)
            ],
            author: course.author
        }
        , requestConfig)
}

export const axiosGetCourseById = (courseId) => {
    return axios.get(`${SERVER_PATH}/courses/${courseId}`, requestConfig)
}

export const axiosDeleteCourseById = (courseId) => {
    return axios.delete(`${SERVER_PATH}/courses/${courseId}`, requestConfig)
}

export const axiosGetAllLessonsByCourseId = (courseId) => {
    return axios.get(`${SERVER_PATH}/courses/${courseId}/lessons`, requestConfig)
}

export const axiosGetAllAvailableCourses = () => {
    return axios.get(`${SERVER_PATH}/courses/available`, requestConfig)
}

/*LESSONS*/

export const axiosCreateOrUpdateLesson = (lesson) => {
    return axios.post(`${SERVER_PATH}/lessons`,
        {
            id: lesson.id,
            name: lesson.name,
            courseId: lesson.courseId,
            contentType: lesson.contentType,
            content: lesson.content,
            teacher: lesson.teacher,
            status: lesson.status,
            author: lesson.author
        }
        , requestConfig)
}

export const axiosGetLessonById = (lessonId) => {
    return axios.get(`${SERVER_PATH}/lessons/${lessonId}`, requestConfig)
}

export const axiosDeleteLessonById = (lessonId) => {
    return axios.delete(`${SERVER_PATH}/lessons/${lessonId}`, requestConfig)
}


export const axiosGetAllTasksByLessonId = (lessonId) => {
    return axios.get(`${SERVER_PATH}/lessons/${lessonId}/tasks`, requestConfig)
}


/*TASKS*/

export const axiosCreateOrUpdateTask = (task) => {
    return axios.post(`${SERVER_PATH}/tasks`,
        {
            id: task.id,
            name: task.name,
            deadline: task.deadline,
            description: task.description,
            answers: [
                ...(task.answers)
            ]
        }
        , requestConfig)
}

export const axiosGetTaskById = (taskId) => {
    return axios.get(`${SERVER_PATH}/tasks/${taskId}`, requestConfig)
}

export const axiosDeleteTaskById = (taskId) => {
    return axios.delete(`${SERVER_PATH}/tasks/${taskId}`, requestConfig)
}

export const axiosGetAllAnswerByTaskId = (taskId) => {
    return axios.get(`${SERVER_PATH}/tasks/${taskId}/answer`, requestConfig)
}
