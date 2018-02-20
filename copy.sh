PROJECT_DIR=../fj-lib
BRANCH=release/0.2.2.9-b4

declare -a MODS=("fj-core" "fj-ext" ) 

## now loop through the above array
for curr in "${MODS[@]}"
do
   echo "${curr}"
   if [ ! -f "${curr}" ]; then
   	echo "File no found ${curr}! creating.."
	mkdir "${curr}"
   fi
   cp -fr ${PROJECT_DIR}/${curr}/target/apidocs/ ${curr}
done
