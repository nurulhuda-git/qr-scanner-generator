build:
	docker-compose build
run:
	docker-compose up
stop:
	docker-compose down
stop-release:
	docker-compose down --rmi local