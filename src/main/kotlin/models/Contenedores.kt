package models

/**
 * @author Mario Resa y Sebastian Mendoza
 */
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import org.jetbrains.kotlinx.dataframe.annotations.DataSchema
import java.io.File
import java.text.Normalizer

@DataSchema
@Serializable
@SerialName("Contenedores")
data class Contenedores(
    @XmlElement(true)
    val codIntCont: String,
    @XmlElement(true)
    val tipoCont: String,
    @XmlElement(true)
    val modeloCont: String,
    @XmlElement(true)
    val descModeloCont: String,
    @XmlElement(true)
    val cantidadCont: Int,
    @XmlElement(true)
    val loteCont: String,
    @XmlElement(true)
    val distritoCont: String,
    @XmlElement(true)
    val barrioCont: String?,
    @XmlElement(true)
    val viaCont: String,
    @XmlElement(true)
    val nomViaCont: String,
    @XmlElement(true)
    val numCalleCont: String,
    @XmlElement(true)
    val coorXCont: String,
    @XmlElement(true)
    val coorYCont: String,
    @XmlElement(true)
    val longiCont: String,
    @XmlElement(true)
    val latiCont: String,
    @XmlElement(true)
    val dirCompletaCont: String

)

/**
 * loadCsvCont() Funcion que lee el CSV facilitado y lo guarda en una lista de tipo Contenedores
 *
 * @param csvFile Parametro de tipo File donde se indicara el CSV que se va a leer
 * @return Variable de tipo Lista de Contenedores obtenida en la lectura del CSV
 */
fun loadCsvCont(csvFile: File): List<Contenedores> {
    val contenedores: List<Contenedores> = csvFile.readLines()
        .drop(1)
        .map { it.split(";") }
        .map {
            it.map { campo -> campo.trim() }
            Contenedores(
                codIntCont = it[0],
                tipoCont = it[1],
                modeloCont = it[2],
                descModeloCont = it[3],
                cantidadCont = it[4].toInt(),
                loteCont = it[5],
                distritoCont = arreglarEspacios(it[6]),
                barrioCont = it[7],
                viaCont = it[8],
                nomViaCont = it[9],
                numCalleCont = it[10],
                coorXCont = it[11],
                coorYCont = it[12],
                longiCont = it[13],
                latiCont = it[14],
                dirCompletaCont = it[15]
            )
        }
    return contenedores
}

/**
 * arreglarEspacios() Funcion que remplaza los espacios en formato Unicode a espacios normales
 *
 * @param dato Variable de tipo String al que se le realiza el cambio
 * @return Variable con los espacios cambiados
 */
fun arreglarEspacios(dato: String): String {
    var nuevo = dato.replace("\u00a0".toRegex(), " ")
    if (dato.contains(" - ")){
        nuevo = Normalizer.normalize(dato, Normalizer.Form.NFD).replace(" - ", "-")
    }
    return nuevo.uppercase()
}