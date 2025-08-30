# Docker Setup for Microservices

This project contains two Spring Boot microservices that can be run using Docker and Docker Compose.

## Services

1. **Naming Server (Eureka)**: Service discovery server running on port 8761
2. **Hello Service**: REST service that registers with Eureka, running on port 8080

## Prerequisites

- Docker Desktop installed and running
- Docker Compose (usually comes with Docker Desktop)
- At least 4GB of available RAM

## Quick Start

### 1. Build and Run with Docker Compose

```bash
# Build and start all services
docker-compose up --build

# Or run in detached mode
docker-compose up --build -d
```

### 2. Access the Services

- **Eureka Dashboard**: http://localhost:8761
- **Hello Service**: http://localhost:8080
- **Hello Service Health Check**: http://localhost:8080/actuator/health

### 3. Stop the Services

```bash
# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

## Individual Service Commands

### Build Individual Services

```bash
# Build naming-server
docker build -t naming-server ./naming-server

# Build hello-service
docker build -t hello-service ./hello-service
```

### Run Individual Services

```bash
# Run naming-server
docker run -p 8761:8761 --name eureka-naming-server naming-server

# Run hello-service (after naming-server is running)
docker run -p 8080:8080 --name hello-service \
  -e EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://host.docker.internal:8761/eureka/ \
  hello-service
```

## Docker Compose Configuration

The `docker-compose.yml` file includes:

- **Health checks** for both services
- **Dependency management** (hello-service waits for naming-server)
- **Custom network** for service communication
- **Environment variables** for Docker-specific configurations
- **Port mappings** for external access

## Service Discovery

The hello-service automatically registers with the Eureka naming server. You can:

1. View registered services at http://localhost:8761
2. See service health status
3. Monitor service instances

## Troubleshooting

### Common Issues

1. **Port conflicts**: Ensure ports 8761 and 8080 are available
2. **Memory issues**: Increase Docker Desktop memory allocation
3. **Service not starting**: Check logs with `docker-compose logs [service-name]`

### View Logs

```bash
# View all logs
docker-compose logs

# View specific service logs
docker-compose logs naming-server
docker-compose logs hello-service

# Follow logs in real-time
docker-compose logs -f
```

### Clean Up

```bash
# Remove all containers, networks, and images
docker-compose down --rmi all --volumes --remove-orphans

# Remove all unused Docker resources
docker system prune -a
```

## Development

### Rebuild After Code Changes

```bash
# Rebuild and restart specific service
docker-compose up --build naming-server

# Rebuild and restart all services
docker-compose up --build
```

### Debug Mode

To run with debug logging, modify the docker-compose.yml environment variables:

```yaml
environment:
  - LOGGING_LEVEL_ROOT=DEBUG
```

## Performance Tuning

The Dockerfiles include JVM optimizations:
- `-Xmx512m`: Maximum heap size of 512MB
- `-Xms256m`: Initial heap size of 256MB

Adjust these values based on your system resources and requirements.
