import React from 'react';
import { FormControlLabel, FormLabel, Radio, RadioGroup } from '@mui/material';

export const QuestionCard = ({question}) => {
    return (
        <div className='card-question'>
            <FormLabel  id="demo-radio-buttons-group-label">{question.question}</FormLabel>
            <RadioGroup
                aria-labelledby="demo-radio-buttons-group-label"
                defaultValue="female"
                name="radio-buttons-group"
            >
                <FormControlLabel value="1" control={ <Radio/> } label={question.answer[0]}/>
                <FormControlLabel value="2" control={ <Radio/> } label={question.answer[1]}/>
                <FormControlLabel value="3" control={ <Radio/> } label={question.answer[2]}/>
            </RadioGroup>
        </div>
    );
};
