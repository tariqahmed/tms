package com.aftershox.tms

class Candidate
{
	String firstName
	String lastName
	String email
	String address
	String city
	String stateCode
	String zipCode
	Date dateCreated

  static constraints =
	{
		firstName(blank:false)
		lastName(blank: false)
		zipCode(size:5..5)
		eMail(email:true)
		stateCode(size:2..2)
  }

	static mapping =
	{
		sort dateCreated: "desc"
	}
}
