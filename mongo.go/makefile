OW_USER?=openwhisk
OW_RUNTIME?=$(OW_USER)/action-golang-v1.15:nightly
OW_COMPILER?=$(OW_USER)/action-golang-v1.15:nightly
WSK?=wsk
MAIN=main
PACKAGE=test
SRCS=main.go go.mod go.sum
NAME=module-main
BINZIP=$(MAIN)-bin.zip
SRCZIP=$(MAIN)-src.zip

deploy: package.done $(BINZIP)
	$(WSK) action update $(PACKAGE)/$(NAME) $(BINZIP) --main $(MAIN) --docker $(OW_RUNTIME)

devel: package.done $(SRCZIP)
	$(WSK) action update $(PACKAGE)/$(NAME) $(SRCZIP) --main $(MAIN) --docker $(OW_COMPILER)

$(BINZIP): $(SRCS) $(VENDORS) $(SRCZIP)
	docker run -i $(OW_COMPILER) -compile $(MAIN) <$(SRCZIP) >$(BINZIP)

$(SRCZIP): $(SRCS)
	zip $@ -qr $^

clean:
	-$(WSK) action delete $(PACKAGE)/$(NAME)
	-rm $(BINZIP) $(SRCZIP) package.done test.json
	-rm test.out

test: test.json
	$(WSK) action invoke test/$(NAME) -r | tee -a test.out
	$(WSK) action invoke test/$(NAME) -P test.json -r | tee -a test.out

test.json:
	echo '{ "name": "Mike" }' >test.json

package.done:
	$(WSK) package update $(PACKAGE)
	touch package.done

.PHONY: deploy devel test clean