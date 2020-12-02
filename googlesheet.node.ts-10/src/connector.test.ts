import {
  ColumnToFieldMapper,
  positionToFieldMapper,
  readSheet$,
  rowToRecord,
} from "./connector";

test("rowToRecord returns a record out of a row", () => {
  const row = ["val_1", "val_2", "val_3"];
  const mapper: ColumnToFieldMapper = [
    { field: "field_a", position: 2 },
    { field: "field_b", position: 1 },
    { field: "field_c", position: 0 },
  ];
  const record = rowToRecord(row, mapper);
  expect(record["field_a"]).toBe("val_3");
  expect(record["field_b"]).toBe("val_2");
  expect(record["field_c"]).toBe("val_1");
});

test(`positionToFieldMapper maps all columns to fields and returns no missing columns, 
since all column names in the header have a mapped record field`, () => {
  const header = ["Destinatario", "Titolo", "Testo", "Scadenza"];
  const column_field_map = {
    Destinatario: "fiscal_code",
    Titolo: "subject",
    Testo: "markdown",
    Scadenza: "due_date",
  };
  const expectedMapper: ColumnToFieldMapper = [
    { field: "fiscal_code", position: 0 },
    { field: "subject", position: 1 },
    { field: "markdown", position: 2 },
    { field: "due_date", position: 3 },
  ];
  const { mapper, columnsNotMapped, fieldsNotMapped } = positionToFieldMapper(
    header,
    column_field_map
  );
  expect(mapper.length).toBe(4);
  mapper.forEach((posToFieldMap, i) => {
    expect(posToFieldMap.field).toBe(expectedMapper[i].field);
    expect(posToFieldMap.position).toBe(expectedMapper[i].position);
  });
  expect(columnsNotMapped.length).toBe(0);
  expect(fieldsNotMapped.length).toBe(0);
});

test(`positionToFieldMapper returns the names of the columns for which there is no mapping field`, () => {
  const header = ["Destinatario", "Titolo", "Testo", "Scadenza"];
  const column_field_map = {
    Destinatario: "fiscal_code",
    Titolo: "subject",
    Scadenza: "due_date",
  };
  const expectedMapper: ColumnToFieldMapper = [
    { field: "fiscal_code", position: 0 },
    { field: "subject", position: 1 },
    { field: "due_date", position: 3 },
  ];
  const { mapper, columnsNotMapped, fieldsNotMapped } = positionToFieldMapper(
    header,
    column_field_map
  );
  expect(mapper.length).toBe(3);
  mapper.forEach((posToFieldMap, i) => {
    expect(posToFieldMap.field).toBe(expectedMapper[i].field);
    expect(posToFieldMap.position).toBe(expectedMapper[i].position);
  });
  expect(columnsNotMapped.length).toBe(1);
  expect(columnsNotMapped[0]).toBe("Testo");
  expect(fieldsNotMapped.length).toBe(0);
});

test(`positionToFieldMapper returns the names of the fields for which there is no mapping column`, () => {
  const header = ["Destinatario", "Testo", "Scadenza"];
  const column_field_map = {
    Destinatario: "fiscal_code",
    Titolo: "subject",
    Testo: "markdown",
    Scadenza: "due_date",
  };
  const expectedMapper: ColumnToFieldMapper = [
    { field: "fiscal_code", position: 0 },
    { field: "markdown", position: 1 },
    { field: "due_date", position: 2 },
  ];
  const { mapper, columnsNotMapped, fieldsNotMapped } = positionToFieldMapper(
    header,
    column_field_map
  );
  expect(mapper.length).toBe(3);
  mapper.forEach((posToFieldMap, i) => {
    expect(posToFieldMap.field).toBe(expectedMapper[i].field);
    expect(posToFieldMap.position).toBe(expectedMapper[i].position);
  });
  expect(columnsNotMapped.length).toBe(0);
  expect(fieldsNotMapped.length).toBe(1);
  expect(fieldsNotMapped[0]).toBe("subject");
});

test("the observable returned by readSheet$ emits the records in the test file", (done) => {
  // this is the test sheet
  const spreadsheetId = "17Lz6SadKDJR8c2m7080kIaJc24Azob0Gh9MhAeMVU8I";
  const sheet = "Messaggi";
  const credentialsFile = "./test-data/test-credentials.json";
  const tokenFile = "./test-data/test-token.json";
  readSheet$(credentialsFile, tokenFile, spreadsheetId, sheet).subscribe({
    next: (records) => {
      expect(records.length).toBe(2);
      records.forEach((record) => {
        expect(Object.keys(record).length).toBe(4);
      });
      const oneRecord = records[0];
      expect(oneRecord["fiscal_code"]).toBeDefined();
      expect(oneRecord["due_date"]).toBeDefined();
      expect(oneRecord["subject"]).toBeDefined();
      expect(oneRecord["markdown"]).toBeDefined();
    },
    complete: () => done(),
  });
});
