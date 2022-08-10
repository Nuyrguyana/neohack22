import React, { useState } from 'react';
import { RegistrationForm } from './registrationForm';
import { LoginForm } from "./loginForm";

export const Auth = () => {
    const [isLogin, setIsLogin] = useState(false)

    const toggleFormType = () => {
        setIsLogin((prevState) => !prevState)
    }

    return (
        <div>
            { isLogin
                ? (
                    <>
                        <RegistrationForm/>
                        <p className='req-reg'> Есть аккаунт?
                            <a className='info text-decoration-none' role='button'
                               onClick={ toggleFormType }> Авторизоваться </a>
                        </p>
                    </>
                ) : (
                    <>
                        <LoginForm/>
                        <p className='req-reg'> Нет аккаунта?
                            <a className='info text-decoration-none' role='button'
                               onClick={ toggleFormType }> Зарегистрироваться </a>
                        </p>
                    </>
                )
            }
        </div>
    );
};