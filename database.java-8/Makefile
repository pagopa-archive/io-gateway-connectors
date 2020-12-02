DB_TYPE?=mysql

.PHONY: preflight build_mysql build_oracle build_sqlserver build_postgresql test release_mysql release_oracle release_sqlserver release_postgresql snapshot clean deploy

release:
	$(MAKE) build
	cp build/distributions/io-gateway-java.zip "build/distributions/$(DB_TYPE).java-8.zip"

release_mysql:
	$(MAKE) DB_TYPE=mysql release

release_oracle:
	$(MAKE) DB_TYPE=oracle release

release_sqlserver:
	$(MAKE) DB_TYPE=sqlserver release

release_postgresql:
	$(MAKE) DB_TYPE=postgresql release

clean:
	./gradlew clean

build: preflight
	./build.sh $(DB_TYPE)

build_mysql:
	$(MAKE) DB_TYPE=mysql build

build_oracle:
	$(MAKE) DB_TYPE=oracle build

build_sqlserver:
	$(MAKE) DB_TYPE=sqlserver build

build_postgresql:
	$(MAKE) DB_TYPE=postgresql build

test:
	./test.sh

snapshot:
	git tag $(shell date +%Y.%m%d.%H%M-snapshot)
	git push origin master --tags

deploy:
	wsk action update iosdk/import build/distributions/io-gateway-java.zip --main Main --docker openwhisk/actionloop-java-v8:nightly

preflight:
	echo "checking required versions"
	java -version  2>&1 |  grep 1.8
