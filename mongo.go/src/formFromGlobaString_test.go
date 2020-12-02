package main

import (
	"testing"
)

func TestReadFormAsMapStatic(t *testing.T) {
	form := readFormAsMapsStatic()
	if form == nil {
		t.Errorf("Form is nil")
	}
	if len(form) != 4 {
		t.Errorf("Form config should contain 4 fields but rather it contains %v fields", len(form))
	}
	var note map[string]interface{}
	// fmt.Println("note>>>>>", reflect.TypeOf(note))
	for _, v := range form {
		if v["name"] == "note" {
			note = v
		}
	}
	if note["name"] == "" {
		t.Errorf("Form field note not found")
	}

}

func TestRespForFormStatic(t *testing.T) {
	resp := respForFormStatic()
	// fmt.Println("resp static >>>>>>>", resp)
	body := resp["body"]
	if body == nil {
		t.Errorf("Body is nil")
	}

	form, okForm := body.(map[string]interface{})["form"]
	if !okForm {
		t.Errorf("Form is nil - %v", form)
	}
	var note map[string]interface{}
	// fmt.Println("note>>>>>", reflect.TypeOf(note))
	for _, v := range form.([]map[string]interface{}) {
		fieldDesc := v
		if fieldDesc["name"] == "note" {
			note = fieldDesc
		}
	}
	if note["name"] == "" {
		t.Errorf("Form field note not found")
	}
	if note["name"] != "note" {
		t.Errorf("Form field note has not the expected name bur rather %v - %v - %v - %v", note["name"], note, form, body)
	}
}
