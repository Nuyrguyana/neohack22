import './App.css';
import { BrowserRouter } from 'react-router-dom';
import { AppRouter } from './container/appRouter';

export const App = () => {
    return (
        <div className="app">
            <BrowserRouter>
                <AppRouter/>
            </BrowserRouter>
        </div>
    );
}

