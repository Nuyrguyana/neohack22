import React, { useRef } from 'react';
import { useForm } from 'react-hook-form';
import { EMAIL_REGEXP } from "../utils/consts";
import { axiosRegisterUser } from "../api/axios/axiosClient";

export const RegistrationForm = () => {
    const { register, handleSubmit, watch, formState: { errors } } = useForm();

    const password = useRef({});
    password.current = watch("password", "");

    const onSubmit = (data) => {
        console.log(data);
        axiosRegisterUser(data)
            .then(r => console.log(r))
    }

    return (
        <form className='container-form-auth' onSubmit={ handleSubmit(onSubmit) }>
            <h2>Регистрация</h2>
            <div className='container-radio-auth'>
                <label className='label-auth'>Имя</label>
                <input className='input-auth' { ...register("firstName", {
                    required: true,
                    pattern: {
                        value: /^[A-Za-z]+$/i,
                        message: 'Имя должно содержать только латинские буквы'
                    }
                }) } />
                { errors.firstName && <span style={ { color: 'red' } }>{ errors.firstName.message }</span> }
            </div>

            <div className='container-radio-auth'>
                <label className='label-auth'>Фамилия</label>
                <input className='input-auth' { ...register("lastName", {
                    required: true,
                    pattern: {
                        value: /^[A-Za-z]+$/i,
                        message: 'Фамилия должна содержать только латинские буквы'
                    }
                }) } />
                { errors.lastName && <span style={ { color: 'red' } }>{ errors.lastName.message }</span> }
            </div>

            <div className='container-radio-auth'>
                <label className='label-auth'>Дата рождения</label>
                <input className='input-auth' type="date" { ...register("birthDate", {
                    required: true
                }) } />
            </div>

            <div className='container-radio-auth'>
                <input className='radio-auth' type="radio" { ...register('role') } value="teacher"/>
                <label className='label-auth' htmlFor='teacher'>Обучаю</label>
                <input className='radio-auth' type="radio" { ...register('role') } value="student"/>
                <label className='label-auth' htmlFor='student'>Изучаю</label>
            </div>

            <div className='container-radio-auth'>
                <label className='label-auth'>Интересы</label>
                <select className='input-auth' { ...register("interests", { required: true }) }>
                    <option value="Frontend">Frontend</option>
                    <option value="Backend">Backend</option>
                    <option value="Analytics">Data Analyst</option>
                    <option value="Data Science">Data Science</option>
                    <option value="Data Analytics">Data Analytics</option>
                    <option value="Q&A">Q&A</option>
                    <option value="NodeJS">NodeJS</option>
                    <option value="DevOps">DevOps</option>
                </select>
            </div>

            <div className='container-radio-auth'>
                <label className='label-auth'>Электронная почта</label>
                <input className='input-auth' { ...register("email", {
                    required: true,
                    pattern: EMAIL_REGEXP
                }) } />
                { errors.email && <span style={ { color: 'red' } }>Электронная почта должна быть правильной</span> }
            </div>

            <div className='container-radio-auth'>
                <label className='label-auth'>Пароль</label>
                <input className='input-auth' type='password' { ...register("password", {
                    required: "You must specify a password",
                    minLength: {
                        value: 8,
                        message: "Пароль должен содержать не менее 8 символов"
                    }
                }) } />
                { errors.password && <span style={ { color: 'red' } }>{ errors.password.message }</span> }
            </div>

            <div className='container-radio-auth'>
                <label className='label-auth'>Повторите пароль</label>
                <input className='input-auth' type="password" { ...register("password_repeat", {
                    validate: value =>
                        value === password.current || "Пароли не совпадают"
                }) }
                />
                { errors.password_repeat && <p style={ { color: 'red' } }>{ errors.password_repeat.message }</p> }
            </div>
            <input className='btn-submit' type="submit"/>
        </form>
    );
};