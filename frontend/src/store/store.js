import { createStore } from 'redux';

const defaultState = {
    setAuth: () => {
    }
}
const reducer = (state = defaultState, action) => {
    switch (action.type) {
        case 'AUTH':
            return {
                ...state,
                setAuth: action.payload.setAuth
            }
        default:
            return state
    }
}

const store = createStore(reducer);

export default store;