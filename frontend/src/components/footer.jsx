import React from "react";
import { Link } from 'react-router-dom';


export const Footer = () => {
    return (
        <div className='footer'>
            <div className='content-footer'>
                <div className='card-content'>
                    <a className='brand' href='/'>IT-BRAINS</a>
                    <Link className='link-footer' to='/'>Мероприятия</Link>
                    <Link className='link-footer' to='/'>Профессии</Link>
                    <Link className='link-footer' to='/'>Контакты</Link>
                    <Link className='link-footer' to='/'>Условия сервиса</Link>
                </div>
            </div>
        </div>
    )
};