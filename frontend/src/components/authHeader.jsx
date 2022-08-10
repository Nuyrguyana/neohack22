import React from 'react';
import brain from '../image/265-2656199_brain-icon-hd-png-download-PhotoRoom.png';
import Box from '@mui/material/Box';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';

export const AuthHeader = () => {
    return (
        <Box sx={ { flexGrow: 1 } }>
            <AppBar sx={ { background: '#9d88fc' } } position="static">
                <Toolbar>
                    <p>IT-BRAINS</p>
                    <img className='logo-img' src={brain} width={50}/>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

