import React from 'react';
import { useHistory } from 'react-router-dom';
import { COURSE_ROUTE } from '../utils/consts';

export const CreateCourse = () => {
    const history = useHistory()

    const handleBackToCourses = () => {
        history.push(COURSE_ROUTE)
    }

    return (
        <div>
            <a className='link-back' onClick={handleBackToCourses}>Вернуться к списку курсов</a>
            <form className='container-form-auth'>
                <h2>Создать курс</h2>
                <div className='container-radio-auth'>
                    <label className='label-auth'>Название курса</label>
                    <input className='input-auth'/>
                </div>

                <div className='container-radio-auth'>
                    <label className='label-auth'>Обложка</label><br/>
                    <input type='file'/>
                </div>

                <div className='container-radio-auth'>
                    <label className='label-auth'>Описание</label>
                    <input className='input-auth'/>
                </div>

                <div className='container-radio-auth'>
                    <label className='label-auth'>Урок</label>
                    <input className='input-auth'/>
                </div>

                <div className='container-radio-auth'>
                    <label className='label-auth'>Тема</label>
                    <input className='input-auth'/>
                </div>

                <button className='btn-submit' type="submit">готово</button>
            </form>
        </div>
    );
};

