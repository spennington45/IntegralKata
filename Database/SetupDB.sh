# User name of who is signing in and password
echo Username:
read username
echo Password:
read -s password 

export PGPASSWORD=${password}

echo Starting script...
psql -U ${username} -f "./dropdb.sql" &&
createdb -U ${username} intigral &&
psql -U ${username} -d intigral -f "./intigral.sql" 
echo Schema has been run ending script.
