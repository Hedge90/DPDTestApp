NOTES:
The instructions requested that it should be possible to remove only the personally identifying data (under GDPR) for a person. Since all data listed in the instructions classify as such, at present the method used for this purpose clears all data for the given person, leaving only an empty row with an id. The method can be easily customized to clear or keep any given field.

As to input data validation, the code only checks for invalid values (e.g. email addresses in wrong format), but accepts empty and null values. This can be further customised to suit business needs.

INFO FOR FRONTEND:

GET /api/persons?index={index}length={length}
    Returns a list of [length] persons based on id, starting from the index.
    Default length is 10, index is 1.

GET /api/persons?id={id}
    Returns the person with the given id.

POST /api/persons
    Saves the person sent in the request into the database. Expects a json in the following format:
        {
            "name": "Joe Apartmentsky",
            "dateOfBirth": "1890-12-12",
            "placeOfBirth": "Washington",
            "mothersName": "Joanne Joinsky",
            "tajNumber": 1212121212,
            "taxId": 1234567890,
            "email": "joe@gmail.com",
            "addresses": [{
                "zipCode": 7900,
                "city": "Washington",
                "streetName": "Main road",
                "streetNumber": "5/B",
                "floorNumber": "5",
                "doorNumber": "3"
                },
                {
                "zipCode": 1000,
                "city": "London",
                "streetName": "Main road",
                "streetNumber": "5/B",
                "floorNumber": "5",
                "doorNumber": "3"
            }]
            "phoneNumbers": ["123456789"]
        }

PUT api/persons?id={id}
    Expects a json in the same format as POST api/persons, and updates the data of the person with the given id to the data sent in the request. Keep in mind that all fields of the given person will be overwritten, so this expects the full set of data in the json.

DELETE api/persons?id={id}GDPROnly={bool}
    If GDPROnly is false (default), the person with the given Id will be deleted from the database, and a confirmation message will be returned.
    If GDPROnly is true, only personally-identifiable data will be deleted, and a PersonDTO with the person's current data will be returned.