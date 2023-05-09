/* 
    Basil Abdul Majeed khan 441009192
    Raji radhwan booqis 441007587
    Maan Muhammad bayamin 441004501
    rowed mohammed qurban S439009995
*/

function validate() {
    let x = document.forms["regform"]["fname"].value;
    if (x == "") {
        alert("First name must be filled out");
        return false;
    }

    x = document.forms["regform"]["lname"].value;
    if (x == "") {
        alert("Last name must be filled out");
        return false;
    }

    x = document.forms["regform"]["pho"].value;
    if (x == "") {
        alert("Phone number must be filled out");
        return false;
    } else if (!validatePho(x)) {
        alert("phone number must follow the pattern: 12_345_6789");
        return false;
    }

    x = document.forms["regform"]["email"].value;
    if (x == "") {
        alert("Email must be filled out");
        return false;
    } else if (!validateEmail(x)) {
        alert("Email must follow the pattern: example@hotmail.com");
        return false;
    }

    x = document.forms["regform"]["bDate"].value;
    if (x == "") {
        alert("Date of birth must be filled out");
        return false;
    }

    x = document.forms["regform"]["pass"].value;
    if (x == "") {
        alert("password must be filled out");
        return false;
    }

    y = document.forms["regform"]["rePass"].value;
    if (x == "") {
        alert("confirmation password must be filled out");
        return false;
    } else if (y != x) {
        alert("confirmation does not match password");
        return false;
    }

}

function validatePho(input_str) {
    var re = /^([05](\d{1})[- ]?(\d{3})[- ]?(\d{4}))$/;
    return re.test(input_str);
}

function validateEmail(input) {
    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    return validRegex.test(input);

}



