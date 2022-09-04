package com.emincingoz.librarymanagement.infrastructure.nationalityPeopleValidator.fake;

import com.emincingoz.librarymanagement.infrastructure.nationalityPeopleValidator.NationalityPeopleModel;
import com.emincingoz.librarymanagement.infrastructure.nationalityPeopleValidator.NationalityPeopleValidator;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fakeNationalityPeopleValidator")
public class FakeNationalityPeopleValidator extends NationalityPeopleValidator {
    @Override
    public boolean validate(NationalityPeopleModel model) throws UnirestException {
        return (!super.checkByNationalityNumberValue(model.getTckno()) == false);
    }
}
