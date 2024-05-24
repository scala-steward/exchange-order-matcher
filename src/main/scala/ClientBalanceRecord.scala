import AssetAmount._
import ClientName._
import UsdAmount._
import zio.parser._
import zio.prelude._

final case class ClientBalanceRecord(
    clientName: ClientName,
    usdBalance: UsdAmount,
    balanceA: AssetAmount,
    balanceB: AssetAmount,
    balanceC: AssetAmount,
    balanceD: AssetAmount
)

object ClientBalanceRecord:

  implicit val ClientBalanceRecordEqual: Equal[ClientBalanceRecord] =
    Equal.default

  val syntax = {
    val tupleSyntax =
      ClientName.syntax
        ~ tabChar
        ~ UsdAmount.syntax
        ~ tabChar
        ~ AssetAmount.syntax
        ~ tabChar
        ~ AssetAmount.syntax
        ~ tabChar
        ~ AssetAmount.syntax
        ~ tabChar
        ~ AssetAmount.syntax

    tupleSyntax.transform(
      ClientBalanceRecord.apply.tupled,
      x => (x.clientName, x.usdBalance, x.balanceA, x.balanceB, x.balanceC, x.balanceD)
    ) ?? "ClientBalanceRecord"
  }