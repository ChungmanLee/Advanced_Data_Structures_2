package ie.ucd.csnl.comp47500.api;

public interface Contact extends Comparable<Contact> {

  
  String getName();

  String getNumber();

  String getEmail();

  String getPhotoUri();

  String getOrganisation();

  String[] getSecondaryEmails();

  boolean isFavorite();
  
  String getId();

  
  void setId(String id);
  
  void setName(String name);

  void setNumber(String number);

  void setEmail(String email);

  void setPhotoUri(String photoUri);

  void setOrganisation(String organisation);

  void setSecondaryEmails(String[] secondaryEmails);

  void setFavorite(boolean favorite);
}
