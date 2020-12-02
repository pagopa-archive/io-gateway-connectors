import { oAuth2Client$ } from "./observables-auth";

test("the observable returned by oAuth2Client$ emits the oAuth2Client", (done) => {
  const credentialsFile = "./test-data/test-credentials.json";
  const tokenFile = "./test-data/test-token.json";
  oAuth2Client$(credentialsFile, tokenFile).subscribe({
    next: (oAuthClient) => {
      expect(oAuthClient.constructor.name).toEqual("OAuth2Client");
      done();
    },
  });
});

test("the observable returned by oAuth2Client$ errors since the credentialsFile does not exist", (done) => {
  const credentialsFile = "none.json";
  const tokenFile = "./test-data/test-token.json";
  oAuth2Client$(credentialsFile, tokenFile).subscribe({
    next: () => {
      throw "should never arrive here";
    },
    error: (err) => {
      expect(err.code).toBe("ENOENT");
      done();
    },
  });
});
