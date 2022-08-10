import React from 'react';
import { FormControl } from '@mui/material';
import Button from '@mui/material/Button';
import { questions } from '../api/mockAPI/mockApi';
import { QuestionCard } from '../components/questionCard';

function SendIcon() {
    return null;
}

export const TestContainer = () => {

    return (
        <FormControl className='container-tickets'>
            { questions.map((question) => {
                return (
                    <QuestionCard question={ question }
                                  key={ question.id }/>
                )
            }) }
            <Button className='btn-ticket' variant="contained" endIcon={ <SendIcon/> }>
                Send
            </Button>
        </FormControl>
    );
};

