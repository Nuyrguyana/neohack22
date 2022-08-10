import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {axiosGetAllLessonsByCourseId} from "../../api/axios/axiosClient";

export const CourseCardMUI = ({courseInfo}) => {

    const handleClick = () => {
        axiosGetAllLessonsByCourseId(courseInfo.id)
            .then(resp => console.log(resp.data))
    }

    return (
        <Card sx={ { minWidth: 275 } }>
            <CardContent>
                <Typography sx={ { fontSize: 14 } } color="text.secondary" gutterBottom>
                    {courseInfo.status}
                </Typography>

                <Typography variant="h5" component="div">
                    {courseInfo.name}
                </Typography>

                <Typography sx={ { mb: 1.5 } } color="text.secondary">
                    {courseInfo.interestTags.map(it => it.name+ ", ")}
                </Typography>

                <Typography variant="body2">
                    {courseInfo.description}
                </Typography>
            </CardContent>

            <Button size="small" onClick={handleClick}>Learn More</Button>
        </Card>
    );
}
