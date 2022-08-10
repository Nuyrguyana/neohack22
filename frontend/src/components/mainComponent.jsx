import React from 'react';
import img from '../image/Programmer-PNG-Photos-PhotoRoom (1).png'
import img2 from '../image/147-e1541573547815-PhotoRoom.png'
import img3 from '../image/images-PhotoRoom.png'

export const MainComponent = () => {
    return (
        <div className='container-info-main-theme'>
            <div className='info-main-theme'>
                <img className='img2-main' src={ img } width={ 200 }/>
                <h1>IT-Brain – ваш первый шаг к профессии будущего!</h1>
                <p>
                    Давно мечтал обрести перспективную и востребованную профессию?
                    Отныне ничего не мешает это сделать!
                    Наша образовательная платформа готовит выпускников, за которыми охотятся крупнейшие компании.
                </p>
            </div>
            <div className='info-main-theme'>
                <img className='img-main' src={ img2 } width={ 300 }/>
                <h1>Обучаем с нуля профессиям, а также помогаем расширить степень своих компетенций</h1>
                <p>
                    Мы даем знания, основанные на практике, которые помогут вам начать строить карьеру в сфере
                    Информационных технологий.
                    Только актуальные темы, востребованные навыки и скиллы. Вы сможете стать специалистом с нуля и
                    начать карьеру через несколько месяцев.
                </p>
            </div>
            <div className='info-main-theme'>
                <img className='img2-main' src={ img3 } width={ 300 }/>
                <h1>Проработанные программы обучения и обширная база знаний</h1>
                <p>
                    <ul>
                        <li>
                            Материалы, представленные в трех разных форматах.
                        </li>
                        <li>
                            Тестовые задания для отработки навыков и обратная связь от преподавателей.
                        </li>
                        <li>
                            Экспертное комьюнити.
                        </li>
                    </ul>
                </p>
                <p className='last-text'>Получите знания, которые позволят вам освоить профессию мечты уже сегодня!</p>
            </div>
        </div>
    );
};