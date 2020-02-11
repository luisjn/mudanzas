package com.luisjn.backend.util.factory;

import com.luisjn.backend.exception.DatosNoValidosException;
import com.luisjn.backend.model.DiaDeTrabajo;
import com.luisjn.backend.model.Elemento;
import com.luisjn.backend.model.Mudanza;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

@Component
public class MudanzaFactory {
    private static final int MINIMO_DIAS_A_TRABAJAR = 1;
    private static final int MINIMO_NUMERO_DE_ELEMENTOS = 1;
    private static final int PESO_MINIMO_ELEMENTO = 1;
    private static final int MAXIMO_DIAS_A_TRABAJAR = 500;
    private static final int MAXIMO_NUMERO_DE_ELEMENTOS = 100;
    private static final int PESO_MAXIMO_ELEMENTO = 100;
    private static final String DIAS_A_TRABAJAR_NO_VALIDO = "El numero de dias a trabajar debe estar entre 1 y 500";
    private static final String NUMERO_DE_ELEMENTOS_NO_VALIDO = "El numero de elementos a mover por dia debe estar entre 1 y 100";
    private static final String PESO_ELEMENTO_NO_VALIDO = "El peso de un elemento a mover debe estar entre 1 y 100";

    public Mudanza crearMudanza(String dni, MultipartFile file) throws IOException {
        List<Integer> data = extraerDatosDelArchivo(file);
        int diasATrabajar = data.remove(0);
        validarDiasATrabajar(diasATrabajar);
        List<DiaDeTrabajo> diasDeTrabajo = obtenerDiasDeTrabajo(diasATrabajar, data);

        return new Mudanza(diasATrabajar, dni, diasDeTrabajo);
    }

    private List<Integer> extraerDatosDelArchivo(MultipartFile file) throws IOException {
        List<Integer> data = new ArrayList<>();
        Scanner scanner = new Scanner(file.getInputStream());

        while (scanner.hasNextLine()) {
            data.add(parseInt(scanner.nextLine()));
        }

        scanner.close();

        return data;
    }

    private List<DiaDeTrabajo> obtenerDiasDeTrabajo(int diasATrabajar, List<Integer> data) {
        List<DiaDeTrabajo> diasDeTrabajo = new ArrayList<>();
        List<Elemento> elementos;
        int numeroItems;

        for (int i = 0; i < diasATrabajar; i++) {
            numeroItems = data.remove(0);
            validarNumeroDeElementos(numeroItems);
            elementos = new ArrayList<>();
            for (int j = 0; j < numeroItems; j++) {
                validarPesoElemento(data.get(0));
                elementos.add(new Elemento(data.get(0)));
                data.remove(0);
            }

            diasDeTrabajo.add(new DiaDeTrabajo(numeroItems, elementos));
        }

        return diasDeTrabajo;
    }

    private void validarDiasATrabajar(int diasATrabajar) {
        if (diasATrabajar < MINIMO_DIAS_A_TRABAJAR || diasATrabajar > MAXIMO_DIAS_A_TRABAJAR) {
            throw new DatosNoValidosException(DIAS_A_TRABAJAR_NO_VALIDO);
        }
    }

    private void validarNumeroDeElementos(int numeroDeElementos) {
        if (numeroDeElementos < MINIMO_NUMERO_DE_ELEMENTOS || numeroDeElementos > MAXIMO_NUMERO_DE_ELEMENTOS) {
            throw new DatosNoValidosException(NUMERO_DE_ELEMENTOS_NO_VALIDO);
        }
    }

    private void validarPesoElemento(int peso) {
        if (peso < PESO_MINIMO_ELEMENTO || peso > PESO_MAXIMO_ELEMENTO) {
            throw new DatosNoValidosException(PESO_ELEMENTO_NO_VALIDO);
        }
    }
}
