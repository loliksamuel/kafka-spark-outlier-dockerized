function ensure-docker-up() {
	CONTAINERS=$(docker ps 2>&1)
	echo "docker output: ${CONTAINERS}"
	if [[ "$CONTAINERS" == *"IMAGE"* ]] ; then
		echo "docker is up"
	else
		echo "please initialize docker env"
		exit -1	
	fi	
}

function delete-container-for-image() {
	IMAGE_NAME="$1"
	echo "About to delete containers for image: "$IMAGE_NAME""
	CONTAINERS_WITH_IMAGE=$(docker ps -a  --format "{{.ID}}" --filter ancestor="$IMAGE_NAME")
	echo "CONTAINERS_WITH_IMAGE "$IMAGE_NAME":"${CONTAINERS_WITH_IMAGE}""
	if [[ -z "${CONTAINERS_WITH_IMAGE// }" ]] ; then
		echo "No containers with image:"$IMAGE_NAME""
	else
		echo "Deleting containers and their volumes: "$CONTAINERS_WITH_IMAGE""
		docker rm -v "$CONTAINERS_WITH_IMAGE"
	fi
}	

function delete-image() {
	IMAGE_NAME="$1"
	echo "About to delete image for: "$IMAGE_NAME""
	
	FOUND_IMAGE=$(docker images --quiet=true "$IMAGE_NAME")
	echo "FOUND_IMAGE: "$FOUND_IMAGE""
	if [[ -z "${FOUND_IMAGE// }" ]] ; then
		echo "No image for:"$IMAGE_NAME""
	else
		echo "Deleting image: "$FOUND_IMAGE""
		docker rmi  "$FOUND_IMAGE"
	fi
}

function delete-container-and-image() {
	IMAGE_NAME="$1"
	echo "About to delete containers and image for: "$IMAGE_NAME""
	
	delete-container-for-image "$IMAGE_NAME" && \
	delete-image "$IMAGE_NAME"	
}
