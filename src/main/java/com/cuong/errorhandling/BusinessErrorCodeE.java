package com.cuong.errorhandling;

public enum BusinessErrorCodeE {
	UnHandledException(0),

	ValidationInvalidParameter(40000001),

	InternalServerError(500000),

	DBConcurrency(20100001),

	VMSModuleDoorOpen(16100001),

	BookNotFound(90900001),

	BookExsited(90900002),

	InvalidParam(40000),

	DuplicateCode(500001),
	
	DuplicateBookId(500002);

	private final int id;

	BusinessErrorCodeE(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}
}