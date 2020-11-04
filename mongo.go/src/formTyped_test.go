package main

import (
	"testing"
)

func TestReadForm(t *testing.T) {
	form := ReadForm()
	if form == nil {
		t.Errorf("Form is nil")
	}
	if len(form) != 3 {
		t.Errorf("Form config should contain 3 fields but rather it contains %v fields", len(form))
	}
	var note FormField
	for _, v := range form {
		if v.Name == "note" {
			note = v
		}
	}
	if note.Name == "" {
		t.Errorf("Form field note not found")
	}

}
