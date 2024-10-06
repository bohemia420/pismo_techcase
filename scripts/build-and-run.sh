#!/usr/bin/env bash

build_image() {
    echo "Building Docker image..."
    docker build -t pismo_techcase .
    if [ $? -ne 0 ]; then
        echo "Failed to build the Docker image."
        exit 1
    fi
    echo "Docker image built successfully."
}

run_container() {
    echo "Running the Docker container..."
#    docker run -p 8080:8080 pismo_techcase
    docker-compose up --build -d
}

if [[ "$1" == "--build" ]]; then
    build_image
fi

run_container
