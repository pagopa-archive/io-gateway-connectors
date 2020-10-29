import { question$, readFile$, readLine$, writeFile$ } from "./observables";
import { Readable } from "stream";

test("the observable returned by readFile$ emits when the file is read", (done) => {
  readFile$("./src/observables.test.ts").subscribe({
    next: (data) => {
      expect(
        data.toString().includes('readFileObs("./src/observables.test.ts")')
      ).toBeTruthy();
      done();
    },
  });
});

test("the observable returned by readFile$ errors when the file is not found", (done) => {
  readFile$("none").subscribe({
    next: () => {
      throw "should never arrive here";
    },
    error: (err) => {
      expect(err.code).toBe("ENOENT");
      done();
    },
  });
});

test("the observable returned by question$ emits tbe answer", (done) => {
  const answer = "an answer";
  const enter = "\n";
  const readable = Readable.from([answer + enter]);
  question$("A question", readable).subscribe({
    next: (data) => {
      expect(data).toBe(answer);
      done();
    },
  });
});

test("the observable returned by readLine$ emits the value passed in by the read stream", (done) => {
  const line = "a line";
  const readable = Readable.from([line]);
  readLine$(readable).subscribe({
    next: (data) => {
      expect(data).toBe(line);
      done();
    },
  });
});

test("the observable returned by writeFile$ emits the file name when the file is written", (done) => {
  const fileName = "./test-data/a-file.txt";
  const data = "some data";
  writeFile$(fileName, data).subscribe({
    next: (resp) => {
      expect(fileName).toBe(resp);
      done();
    },
  });
});
