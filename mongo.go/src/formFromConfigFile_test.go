package main

import (
	"testing"
)

func TestReadFormAsMap(t *testing.T) {
	form := ReadFormAsMaps()
	if form == nil {
		t.Errorf("Form is nil")
	}
	if len(form) != 3 {
		t.Errorf("Form config should contain 3 fields but rather it contains %v fields", len(form))
	}
	var message map[string]interface{}
	for _, v := range form {
		if v["name"] == "note" {
			message = v
		}
	}
	if message["name"] == "" {
		t.Errorf("Form field note not found")
	}

}

func TestRespForForm(t *testing.T) {
	resp := respForForm()
	body := resp["body"]
	if body == nil {
		t.Errorf("Body is nil")
	}

	form, okForm := body.(map[string]interface{})["form"]
	if !okForm {
		t.Errorf("Form is nil - %v", form)
	}
	var note map[string]interface{}
	// fmt.Println("Note>>>>>", reflect.TypeOf(note))
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
		t.Errorf("Form field note has not the expected name but rather %v - %v - %v - %v", note["name"], note, form, body)
	}
}
