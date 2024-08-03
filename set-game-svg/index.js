import fs from "fs";
import {svg2png} from "svg-png-converter"
import { createCard } from "./createCard.js";

function getSVGFileName(shape, color, count, fill) {
  return  `${shape}_${color}_${count}_${fill}.svg`;
}

function getPNGFileName(svgFileName) {
  return svgFileName.split("_").map(el =>
    el.charAt(0).toUpperCase() + el.slice(1)
  ).join("_").split(".")[0] + ".png";
}

const count = [null, "one", "two", "three"]; //null no zero string value used.

async function WriteCardsToFile(card) {
  const svgFilepath = `${getSVGFileName(card.shape, card.color, card.count, card.fill)}`;
  const contents = createCard(card.shape, card.color, card.count, card.fill);
  const pngOutput = `${getPNGFileName(svgFilepath)}`

  // fs.writeFile(`svg-deck/${svgFilepath}`, contents, async (err) => {
  //   if (err) console.error(err)
  //   else console.log(`wrote "${svgFilepath}" to file`)
  // })

  const outputBuffer = await svg2png({
    input: contents,
    encoding: "buffer",
    width: 160,
    height: 108,
    format: "png",
  })

  fs.writeFile(`png-deck/card_${pngOutput}`, outputBuffer, (err) => {
    if (err) console.error(err)
    else console.log(`wrote "${pngOutput}" to file `)
  })
}


function app(){
  const deck = [];
  [1, 2, 3].forEach(function(number){
    ['red','purple', 'green'].forEach(function(color){
      ['diamond', 'squiggle', 'oval'].forEach(function(shape){
        ['solid', 'striped', 'open'].forEach(function(fill){
          deck.push({
            count: count[number],
            shape: shape,
            color: color,
            fill: fill
          });
        });
      });
    });
  });

  deck.forEach(card => {
    WriteCardsToFile(card)
  })


}

app();
