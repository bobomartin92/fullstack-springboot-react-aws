import fetch from "unfetch";

const checkStatus = response => {
    if (response.ok){
        return response;
    }

    const error = new Error(response.statusText);
    error.response = response;
    console.log(error)
    return Promise.reject(error)
}

export const getAllStudent = () =>
    fetch("api/v1/students")
        .then(checkStatus);

export const addNewStudent = student =>
    fetch("api/v1/students", {
        headers: {
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify(student)
    }).then(checkStatus);

export const deleteStudent = id =>
    fetch(`api/v1/students/${id}`, {
        method: "DELETE"
    })
        .then(checkStatus);