import React from 'react';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import {Main} from "../components/Main";
import {NoPage} from "../components/NoPage";

export class Routing extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Main/>}>
                        {/*<Route index element={<Main/>}></Route>*/}
                        <Route path="/home" element={<Main/>}></Route>
                        {/*<Route path="*" element={<NoPage/>}></Route>*/}
                    </Route>
                </Routes>
            </BrowserRouter>
        );
    }
}