import jwtDecoder from 'jwt-decode'

export const updateToken = (newToken) => {
    localStorage.removeItem('token')
    localStorage.setItem('token', newToken);
}
export const setMasterToken = () => {
    localStorage.setItem('master_token', "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaXBiaXBAbWFpbC5ydSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfVEVBQ0hFUiJ9XSwiaWF0IjoxNjU5ODE1Njg3LCJleHAiOjE2NjM0MTU2ODd9.AQoSg1cybPqDXo_WG7eCIGpSoPIQbb_OYAnEepif9gA");
}
export const getMasterToken = () => {
    return localStorage.getItem('master_token');
}

export const deleteToken = () => {
    localStorage.removeItem('token')
}

export const isTokenValid = () => {
    const token = localStorage.getItem('token');
    if (!token) {
        return false
    } else {
        const decodedToken = jwtDecoder(token);
        const currentDate = new Date();
        const timeInSeconds = currentDate.getTime() / 1000;
        return (timeInSeconds < decodedToken.exp);
    }
}

export const getRoleFromToken = () => {
    const token = localStorage.getItem('token');

    const decoderToken = jwtDecoder(token)
    return decoderToken.auth[0].authority
}

export const getToken = () => {
    return localStorage.getItem('token');
}
