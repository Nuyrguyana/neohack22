import React, { useEffect, useState } from 'react';
import { Redirect, Route, Switch } from "react-router-dom";
import { useDispatch } from 'react-redux';
import {
    EFFICIENCY_DASHBOARD_ROUTE, FORUM_ROUTE,
    LEARNING_ROUTE,
    LOGIN_ROUTE,
    PROFILE_ROUTE,
    ROOT,
    STATISTICS_ROUTE,
    TEST_ROUTE,
    COURSE_ROUTE, CREATE_COURSE_ROUTE
} from '../utils/consts';
import { CourseContainer } from './courseContainer';
import { MainComponent } from '../components/mainComponent';
import { TestContainer } from './testContainer';
import { ProfileContainer } from './profileContainer';
import { LearningContainer } from './learningContainer';
import { StatisticContainer } from './statisticContainer';
import { EfficiencyDashboardContainer } from './efficiencyDashboardContainer';
import { ForumContainer } from './forumContainer';
import { Auth } from '../components/auth';
import { Header } from '../components/header';
import { Footer } from '../components/footer';
import { AuthHeader } from '../components/authHeader';
import {isTokenValid} from "../api/axios/jwt/jwtLocalStorage";
import { CreateCourse } from '../components/createCourse';

export const AppRouter = () => {
    const dispatch = useDispatch();
    const [isAuth, setAuth] = useState(
        isTokenValid
    )

    useEffect(() => {
        dispatch({
            type: 'AUTH', payload: {
                setAuth: setAuth
            }
        })
    },[])

    return (
        isAuth ?
            <div>
                <Header/>
            <Switch>
                <Route path={ROOT} exact component={MainComponent}/>
                <Route path={TEST_ROUTE} exact component={TestContainer}/>
                <Route path={COURSE_ROUTE} exact component={CourseContainer}/>
                <Route path={PROFILE_ROUTE} exact component={ProfileContainer}/>
                <Route path={LEARNING_ROUTE} exact component={LearningContainer}/>
                <Route path={STATISTICS_ROUTE} exact component={StatisticContainer}/>
                <Route path={EFFICIENCY_DASHBOARD_ROUTE} exact component={EfficiencyDashboardContainer}/>
                <Route path={FORUM_ROUTE} exact component={ForumContainer}/>
                <Route path={CREATE_COURSE_ROUTE} exact component={CreateCourse}/>
                <Redirect to={ ROOT }/>
            </Switch>
                <Footer/>
            </div>
            :
            <div>
                <AuthHeader/>
            <Switch>
                <Route path={LOGIN_ROUTE} exact component={Auth}/>
                <Redirect to={ LOGIN_ROUTE }/>
            </Switch>
            </div>
    );
};

