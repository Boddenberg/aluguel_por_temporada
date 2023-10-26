gradle clean
gradle assemble
docker build -t dream_project .
docker-compose -f docker-compose-dev.yml up