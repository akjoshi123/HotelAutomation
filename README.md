# HotelAutomation

How to run

1) Go to the HOME directory of the application and run 'mvn clean package'.
2) Go to the target directory, run 'java -jar HotelAutomation-1.0-SNAPSHOT.jar'

Assumption
1) Controller is operating at night time.

Note
1) User inputs are accepted after pressing the Return key.
2) The application will exit, when user types 'exit'.
3) A cron will be running in background which keeps a check of interval for which a light bulb is ON. If it exceeds the interval limit, then the cron will switch OFF the light bulb.
4) This cron runs every 5 sec and prints the status of Hotel only when the cron changes the status of Light Bulb or AC to ON/OFF.
4) There is a test case to check functionality of the cron. While executing this test case, the thread will sleep for the interval of time (which is light ON interval time) which is currently 1 minute. This sleep can be avoided by using Mocks but is not implemented since I could'nt get time.
