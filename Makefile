snapshot:
	date "+%Y.%m%d.%H%M-snapshot" | tee .snapshot
	cat .snapshot | xargs git tag 
	git push origin
	git push origin --tags
	cat .snapshot | xargs git tag -d 

