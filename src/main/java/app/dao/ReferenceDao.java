package app.dao;

import java.util.List;

import app.model.Reference;

public interface ReferenceDao {
	Reference createReference(Reference reference);
	boolean deleteReference(String id);
	boolean updateReference(String champ, String value, String id);
	Reference findReferenceById(String id);
	List<Reference> listReference();
}
