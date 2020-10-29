// Implements the async functions as Observables

import { readFile } from "fs";
import * as readline from "readline";
import { writeFile } from "fs";
import { Readable } from "stream";
import { Observable, Observer, TeardownLogic } from "rxjs";

export function readFile$(fileName: string) {
  return new Observable(
    (observer: Observer<Buffer>): TeardownLogic => {
      readFile(fileName, (err, content: any) => {
        if (err) {
          observer.error(err);
          return;
        }
        observer.next(content);
        observer.complete();
      });
    }
  );
}

export function readLine$(readStream: Readable) {
  return new Observable(
    (observer: Observer<string>): TeardownLogic => {
      const rl = readline.createInterface({
        input: readStream,
      });

      rl.on("line", (line: string) => {
        observer.next(line);
      });
      rl.on("close", () => {
        observer.complete();
      });
    }
  );
}
export function readCommandLine$() {
  return readLine$(process.stdin);
}

export function question$(
  question: string,
  readStream: Readable = process.stdin
) {
  return new Observable(
    (observer: Observer<string>): TeardownLogic => {
      const rl = readline.createInterface({
        input: readStream,
      });

      rl.question(question, (answer) => {
        rl.close();
        observer.next(answer);
        observer.complete();
      });
    }
  );
}

export function writeFile$(name: string, data: string) {
  return new Observable(
    (observer: Observer<string>): TeardownLogic => {
      writeFile(name, data, (err) => {
        if (err) {
          observer.error(err);
          return;
        }
        observer.next(name);
        observer.complete();
      });
    }
  );
}
