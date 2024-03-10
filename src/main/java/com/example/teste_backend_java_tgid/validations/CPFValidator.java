package com.example.teste_backend_java_tgid.validations;

import com.example.teste_backend_java_tgid.annotations.CPF;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final String CPF_REGEX = "^[0-9]{11}$";

    @Override
    public void initialize(CPF cpf) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
            return isValidCPF(cpf);
    }

    private boolean validateCpfFormat(String cpf) {
        Pattern pattern = Pattern.compile(CPF_REGEX);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    private boolean isValidCPF(String cpf) {
        if ((cpf==null) || (cpf.length()!=11 || !validateCpfFormat(cpf))) return false;

        if(cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444")
                || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")){
            return false;
        }

        int digito1 = calcularDigito(cpf.substring(0,9));
        int digito2 = calcularDigito(cpf.substring(0,9) + digito1);
        return cpf.equals(cpf.substring(0,9) + digito1 + digito2);
    }

    private static int calcularDigito(String str) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito* CPFValidator.pesoCPF[CPFValidator.pesoCPF.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

}
