import fs from "fs";
import {svg2png} from 'svg-png-converter'
import { createCard } from "../createCard.js";
import { getPNGFileName, getSVGFileName } from "../index.js";


const config = [
  ["diamond", "purple", "two", "striped"],
  ["squiggle", "purple", "two", "striped"],
  ["oval", "purple", "two", "striped"],
  ["diamond", "green", "three", "striped"],
  ["squiggle", "green", "three", "striped"],
  ["oval", "green", "three", "striped"],
  ["diamond", "red", "one", "striped"],
  ["squiggle", "red", "two", "solid"],
  ["oval", "red", "three", "open"],
]

config.forEach(async c => {
  const svgFilepath = `${getSVGFileName(c[0], c[1], c[2], c[3])}`;
  const contents = createCard(c[0], c[1], c[2], c[3]);
  const pngOutput = `${getPNGFileName(svgFilepath)}`

  fs.writeFile(`test/${svgFilepath}`, contents, async (err) => {
    if (err) console.error(err)
    else console.log(`wrote "${svgFilepath}" to file`)
  })

  const outputBuffer = await svg2png({
    input: contents,
    encoding: "buffer",
    format: "png",
  })

  fs.writeFile(`png-test/${pngOutput}`, outputBuffer, (err) => {
    if (err) console.error(err)
    else console.log(`wrote "${pngOutput}" to file `)
  })
})
