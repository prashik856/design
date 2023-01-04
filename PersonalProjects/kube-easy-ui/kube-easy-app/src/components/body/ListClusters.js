import React from 'react';
import Dropdown from 'react-bootstrap/Dropdown';
import axios from "axios";
import {response} from "express";

export class ListClusters extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            data: []
        }
    }

    componentDidMount() {
        axios.get("http://localhost:8081/clusters/all")
            .then(
                (response) => {
                    this.setState({
                        isLoaded: true,
                        data: response.data
                    });
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const {error, isLoaded, data} = this.state;
        if(error) {
            return <div>Error: {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Loading...</div>
        } else {
            return (
                <Dropdown>
                    <Dropdown.Toggle variant="success" id="dropdown-basic">
                        Clusters
                    </Dropdown.Toggle>

                    <Dropdown.Menu>
                        {
                            data.map(
                                item => (
                                    <Dropdown.Item href="#/action-1">{item.clusterName}</Dropdown.Item>
                                )
                            )
                        }
                    </Dropdown.Menu>
                </Dropdown>
            );
        }
    }
}
