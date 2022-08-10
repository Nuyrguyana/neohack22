import React, { useEffect, useState } from 'react';
import { CourseCardMUI } from '../components/MUIcomponets/courseCardMUI';
import { Grid } from '@mui/material';
import { axiosGetAllAvailableCourses } from "../api/axios/axiosClient";
import { getRoleFromToken } from '../api/axios/jwt/jwtLocalStorage';
import { useHistory } from 'react-router-dom';
import { CREATE_COURSE_ROUTE } from '../utils/consts';

export const CourseContainer = () => {
    const [courses, setCourses] = useState([])
    const history = useHistory()

    useEffect(() => {
        axiosGetAllAvailableCourses()
            .then((resp) => {
                console.log("courses", resp.data)
                setCourses(resp.data)
            })
    }, []);
    console.log(getRoleFromToken())

    const handleCreateCourse = () => {
        history.push(CREATE_COURSE_ROUTE)
    }

    return (
        <div className='container-course'>
            <div>
                <button className='btn-submit create-course'
                        onClick={handleCreateCourse}
                >
                    Создать курс
                </button>
            </div>

            <div>
                <Grid container spacing={ { xs: 2 } } columns={ { xs: 1, sm: 8, md: 12 } }
                      sx={ { p: 5, background: '#f3fbff' } }>
                    { courses.map((courseInfo) => {
                            return (
                                <Grid key={ courseInfo } item xs={ 3 } md={ 4 } sm={ 4 }>
                                    <CourseCardMUI courseInfo={ courseInfo }/>
                                </Grid>
                            )
                        }
                    ) }
                </Grid>
            </div>
        </div>
    );
};

