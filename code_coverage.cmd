

echo "Running mvn test coverage (without coverage lol)"

powershell -Command "mvn test";
powershell -Command "mvn jacoco:report";
powershell -Command "mv target/site/jacoco/jacoco.xml cov.xml -Force";
powershell -Command "mv target/site/jacoco/index.html ./codeCoverageResult/index.html -Force";